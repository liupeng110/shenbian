//
//  CHArticleListTableViewCell.m
//  shenbianapp
//
//  Created by book on 2017/9/24.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import "CHArticleListTableViewCell.h"

@interface CHArticleListTableViewCell ()

@property(nonatomic,strong)UIImageView *coverImageView;
@property(nonatomic,strong)UILabel *titleLabel;
@property(nonatomic,strong)UILabel *readLabel;

@end

@implementation CHArticleListTableViewCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier{

    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        
        [self addSubview:self.coverImageView];
        [self.coverImageView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.top.equalTo(self).offset(15);
            make.width.mas_equalTo(120);
            make.height.mas_equalTo(80);
        }];
        
        [self addSubview:self.titleLabel];
        [self.titleLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.coverImageView.mas_right).offset(15);
            make.top.equalTo(self.coverImageView);
            make.right.equalTo(self).offset(-15);
        }];
        
        [self addSubview:self.readLabel];
        [self.readLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.coverImageView.mas_right).offset(15);
            make.bottom.equalTo(self.coverImageView);
            make.right.equalTo(self).offset(-15);
        
        }];
    }
    return self;
}

-(void)layoutSubviews{
    [super layoutSubviews];

    
}

-(void)setIndexPath:(NSIndexPath *)indexPath{
 
    self.titleLabel.text = @"文章标题文章标题文章标题文章标题文章标题文章标题文章标题";
    self.readLabel.text = [NSString stringWithFormat: @"阅读 4  评论 6  喜欢 8"];
}

-(UIImageView *)coverImageView{

    if (!_coverImageView) {
        _coverImageView = [[UIImageView alloc]initWithImage:[UIImage imageNamed:@"sy_sj_cover"]];
    
    }
    return _coverImageView;
}

-(UILabel *)titleLabel{

    if (!_titleLabel) {
        _titleLabel = [UILabel new];
        _titleLabel.font = [UIFont systemFontOfSize:15];
        _titleLabel.textColor = [UIColor colorWithHexString:@"#2d333a"];
        _titleLabel.numberOfLines = 0;
    }
    return _titleLabel;
}

-(UILabel *)readLabel{
    if (!_readLabel) {
        _readLabel = [UILabel new];
        _readLabel.font = [UIFont systemFontOfSize:12];
        _readLabel.textColor = [UIColor colorWithHexString:@"#9b9b9b"];
    }
    return _readLabel;
}

@end
