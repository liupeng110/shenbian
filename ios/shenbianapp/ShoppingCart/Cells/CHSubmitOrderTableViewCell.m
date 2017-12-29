//
//  CHSubmitOrderTableViewCell.m
//  shenbianapp
//
//  Created by book on 2017/12/29.
//  Copyright © 2017年 helinkeji. All rights reserved.
//

#import "CHSubmitOrderTableViewCell.h"

@interface CHSubmitOrderTableViewCell()
@property(nonatomic,strong)UILabel *priceLabel;
@property(nonatomic,strong)UILabel *numLabel;
@property(nonatomic,strong)IQTextView *remarkTextView;
@property(nonatomic,strong)UILabel *remarkLabel;
@end

@implementation CHSubmitOrderTableViewCell

@synthesize numLabel;
@synthesize indexPath;

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        
        self.textLabel.font = [UIFont systemFontOfSize:15];
        self.textLabel.textColor = [UIColor colorWithHexString:@"#2d333a"];
        self.selectionStyle = UITableViewCellSelectionStyleNone;
    }
    return self;
}

-(UILabel *)priceLabel{
    if (_priceLabel == nil) {
        _priceLabel = [UILabel new];
        _priceLabel.font = [UIFont systemFontOfSize:15];
        _priceLabel.textColor = [UIColor colorWithHexString:@"#2d333a"];
    }
    return _priceLabel;
}

-(void)setIndexPath:(NSIndexPath *)indexPath{
    
    if (indexPath.section < self.dataArray.count - 2) {
        
        [self.contentView addSubview:self.priceLabel];
        [self.priceLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.centerY.equalTo(self.contentView);
            make.right.mas_equalTo(self.contentView).offset(-15);
            make.width.mas_equalTo(60);
            make.height.mas_equalTo(20);
        }];
        
        
        [self.contentView addSubview:self.numLabel];
        [self.numLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.centerY.mas_equalTo(self.contentView);
            make.right.equalTo(self.priceLabel.mas_left).offset(-15);
            make.width.mas_equalTo(60);
            make.height.mas_equalTo(20);
        }];
        
        NSDictionary *storeList = self.dataArray[indexPath.section];
        
        NSArray *orderList = [storeList objectForKey:@"carts"];
        NSDictionary *orderDic =  [orderList objectAtIndex:indexPath.row];
        self.priceLabel.text = [NSString stringWithFormat:@"%@",[orderDic objectForKey:@"price"]];
        self.numLabel.text = [NSString stringWithFormat:@"x  %@",[orderDic objectForKey:@"serviceAmount"]];
        self.textLabel.text = [NSString stringWithFormat:@"%@",[orderDic objectForKey:@"serviceTitle"]];
        
    } else {
        
        if (indexPath.section == self.dataArray.count - 2 && indexPath.row == 2) {
            
            self.accessoryType = UITableViewCellAccessoryNone;
            
            [self.contentView addSubview:self.remarkLabel];
            [self.remarkLabel mas_makeConstraints:^(MASConstraintMaker *make) {
                make.left.equalTo(self.contentView).offset(15);
                make.top.equalTo(self.contentView).offset(10);
                make.width.mas_equalTo(40);
                make.height.mas_equalTo(20);
            }];
            
            [self.contentView addSubview:self.remarkTextView];
            [self.remarkTextView mas_makeConstraints:^(MASConstraintMaker *make) {
                make.left.equalTo(self.remarkLabel);
                make.top.equalTo(self.remarkLabel.mas_bottom);
                make.bottom.equalTo(self.contentView);
                make.right.equalTo(self.contentView).offset(-15);
            }];
            self.remarkLabel.text = @"备注";

        } else {
            
            self.accessoryType = UITableViewCellAccessoryDisclosureIndicator;
            self.textLabel.text = self.dataArray[indexPath.section][indexPath.row];
            self.remarkLabel.text = @"";
            self.remarkTextView = nil;
            self.numLabel.text = @"";
            self.priceLabel.text = @"";
        }
        
    }
    
}


-(UILabel *)numLabel{
    
    if (numLabel == nil) {
        numLabel = [UILabel new];
        numLabel.font = [UIFont systemFontOfSize:15];
        numLabel.textColor = [UIColor colorWithHexString:@"#2d333a"];
    }
    return numLabel;
}

-(UILabel *)remarkLabel{
    if (_remarkLabel == nil) {
        _remarkLabel = [UILabel new];
        _remarkLabel.text = @"备注";
        _remarkLabel.textColor = [UIColor colorWithHexString:@"#2d333a"];
        _remarkLabel.font = [UIFont systemFontOfSize:15];
    }
    return _remarkLabel;
}

-(IQTextView *)remarkTextView{
    if (_remarkTextView == nil) {
        _remarkTextView = [[IQTextView alloc]init];
        _remarkTextView.placeholder = @"请输入其他要求";
        _remarkTextView.font = [UIFont systemFontOfSize:13];
    }
    return _remarkTextView;
}


@end
