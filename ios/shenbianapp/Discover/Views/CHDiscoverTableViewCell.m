//
//  CHDiscoverTableViewCell.m
//  shenbianapp
//
//  Created by book on 2017/10/29.
//  Copyright © 2017年 helinkeji. All rights reserved.
//

#import "CHDiscoverTableViewCell.h"
@interface CHDiscoverTableViewCell()<UICollectionViewDelegate,UICollectionViewDataSource,UICollectionViewDelegateFlowLayout>

@property(nonatomic,strong)UIImageView *headImageView;
@property(nonatomic,strong)UILabel *nameLabel;
@property(nonatomic,strong)UILabel *descriptionLabel;
@property(nonatomic,strong)UIButton *editButton;
@property(nonatomic,strong)UILabel *lastTimeLabel;
@property(nonatomic,strong)UIButton *locationButton;
@property(nonatomic,strong)UICollectionView *collectionView;
@end

@implementation CHDiscoverTableViewCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier{

    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        [self addSubview:self.headImageView];
        [self.headImageView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self).offset(15);
            make.top.equalTo(self).offset(25);
            make.width.height.mas_equalTo(60);
        }];
        
        [self addSubview:self.nameLabel];
        [self.nameLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.headImageView.mas_right).offset(10);
            make.top.equalTo(self).offset(25);
            make.height.mas_equalTo(20);
            make.right.equalTo(self).offset(-50);
        }];
        
        [self addSubview:self.descriptionLabel];
        [self.descriptionLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.headImageView.mas_right).offset(10);
            make.top.equalTo(self.nameLabel.mas_bottom).offset(5);
            make.height.mas_equalTo(50);
            make.right.equalTo(self).offset(-15);
        }];
        
        [self addSubview:self.lastTimeLabel];
        [self.lastTimeLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.headImageView.mas_right).offset(10);
            make.top.equalTo(self.descriptionLabel.mas_bottom).offset(5);
            make.width.mas_equalTo(100);
            make.height.mas_equalTo(20);
        }];
        
        [self addSubview:self.locationButton];
        [self.locationButton mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.lastTimeLabel.mas_right);
            make.top.equalTo(self.descriptionLabel.mas_bottom).offset(5);
            make.width.mas_equalTo(120);
            make.height.mas_equalTo(20);
        }];
        
        [self addSubview:self.collectionView];
        [self.collectionView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self).offset(15);
            make.right.equalTo(self).offset(-15);

            make.bottom.equalTo(self).offset(-25);
            make.top.equalTo(self.lastTimeLabel.mas_bottom).offset(15);
        }];

        self.selectionStyle = UITableViewCellSelectionStyleNone;
    
    }
    return self;
}

-(UIImageView *)headImageView{

    if (_headImageView == nil) {
        _headImageView = [[UIImageView alloc]initWithImage:[UIImage imageNamed:@"sy_sj_cover"]];
        _headImageView.layer.cornerRadius = 3;
        _headImageView.clipsToBounds = YES;
    }
    return _headImageView;

}

-(UILabel *)nameLabel{

    if (_nameLabel == nil) {
        _nameLabel = [UILabel new];
        _nameLabel.textColor = [UIColor colorWithHexString:@"#2d333a"];
        _nameLabel.font = [UIFont systemFontOfSize:15];
        _nameLabel.text = @"用户名";
    }
    return _nameLabel;
}

-(UILabel *)descriptionLabel{

    if (_descriptionLabel == nil) {
        _descriptionLabel = [UILabel new];
        _descriptionLabel.textColor = [UIColor colorWithHexString:@"#2d333a"];
        _descriptionLabel.font = [UIFont systemFontOfSize:13];
        _descriptionLabel.text = @"内容我是内容内容我是内容内容我是内容内容我是内容内容我是内容内容我是内容内容我是内容内容我是内容内容我是内容";
        _descriptionLabel.numberOfLines = 0;
    }
    return _descriptionLabel;
}

-(UIButton *)editButton{

    if (_editButton == nil) {
        _editButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_editButton setImage:[UIImage imageNamed:@"fx_bj"] forState:(UIControlStateNormal)];
        [_editButton addTarget:self action:@selector(clickEditButton) forControlEvents:(UIControlEventTouchUpInside)];
    }
    return _editButton;
}

- (void)clickEditButton{

}

-(UILabel *)lastTimeLabel{
    if (_lastTimeLabel == nil) {
        _lastTimeLabel = [UILabel new];
        _lastTimeLabel.textColor = [UIColor colorWithHexColor:@"#8f959c"];
        _lastTimeLabel.font = [UIFont systemFontOfSize:12];
        _lastTimeLabel.text = @"3小时前";
    }
    return _lastTimeLabel;
}

-(UIButton *)locationButton{
    if (_locationButton == nil) {
        _locationButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_locationButton setImage:[UIImage imageNamed:@"fx_dw"] forState:(UIControlStateNormal)];
        [_locationButton setTitle:@"  中关村创业大街" forState:(UIControlStateNormal)];
        [_locationButton.titleLabel setFont:[UIFont systemFontOfSize:12]];
        [_locationButton setTitleColor:[UIColor colorWithHexColor:@"#8f959c"] forState:(UIControlStateNormal)];
    }
    return _locationButton;
}

-(UICollectionView *)collectionView{

    if (_collectionView == nil) {
        UICollectionViewFlowLayout *flowout = [[UICollectionViewFlowLayout alloc]init];
        flowout.minimumLineSpacing = 5;
        flowout.minimumInteritemSpacing = 5;
        _collectionView = [[UICollectionView alloc]initWithFrame:(CGRectZero) collectionViewLayout:flowout];
        _collectionView.dataSource = self;
        _collectionView.delegate = self;
        [_collectionView registerClass:[UICollectionViewCell class] forCellWithReuseIdentifier:@"pictureCell"];
        _collectionView.backgroundColor = [UIColor clearColor];
        _collectionView.scrollEnabled = NO;
        
    }
    
    return _collectionView;

}


-(NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section{

    return 3;
}

-(UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath{

    UICollectionViewCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"pictureCell" forIndexPath:indexPath];
    UIImageView *imageView = [[UIImageView alloc]initWithImage:[UIImage imageNamed:@"sy_sj_cover"]];

    [cell addSubview:imageView];
    [imageView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.edges.equalTo(cell);
    }];

    return cell;
}

-(CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout *)collectionViewLayout sizeForItemAtIndexPath:(NSIndexPath *)indexPath{

    return CGSizeMake(100, 100);
}

-(void)setModelDic:(NSDictionary *)modelDic{
    _modelDic = modelDic;
    self.nameLabel.text = [NSString stringWithFormat:@"%@",[modelDic objectForKey:@"serviceTitle"]];
    self.lastTimeLabel.text = [NSString stringWithFormat:@"%@",[modelDic objectForKey:@"releaseTime"]];
    [self.locationButton setTitle:[NSString stringWithFormat:@"%@",[modelDic objectForKey:@"address"]] forState:(UIControlStateNormal)];
    self.descriptionLabel.text = [NSString stringWithFormat:@"%@",[modelDic objectForKey:@"serviceDescription"]];
}

@end
